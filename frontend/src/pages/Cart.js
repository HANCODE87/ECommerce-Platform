import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axiosInstance from "../api/axiosInstance";
import { getProductById } from "../api/productApi";
import "../styles/cart.css"; // 導入自定義 CSS

const Cart = () => {
  const { userId } = useParams();
  const [cartItems, setCartItems] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        //根據使用者帳號取得購物車資料
        const reponse = await axiosInstance.get(`/orders/user/${userId}`, {
          withCredentials: true,
        });
        //取得所有商品的 id
        const productIds = reponse.data.data.map((item) => item.productId);
        //根據商品 id 取得商品資料並等待所有商品資料請求完成
        const products = await Promise.all(
          productIds.map((productId) => getProductById(productId))
        );

        //將商品資料與購物車資料合併
        const orderInfo = reponse.data.data.map((item, index) => ({
          ...item,
          product: products[index],
        }));
        console.log(orderInfo);
        setCartItems(orderInfo);
        setLoading(false); //取得購物車資料後設定 loading 為 false
      } catch (error) {
        setError(error);
        setLoading(false); //取得購物車資料失敗後設定 loading 為 false
      }
    };
    fetchCartItems();
  }, [userId]); //userId改變時重新取得購物車資料

  //計算總金額
  const totalAmount = cartItems.reduce((total, item) => {
    return total + item.product.prodPrice * item.quantity;
  }, 0);

  //結帳
  const handleCheckout = () => alert("結帳成功");

  const handleDelete = async (productId) => {
    try {
      await axiosInstance.delete(
        `/orders/delete?userId=${userId}&productId=${productId}`,
        {
          withCredentials: true,
        }
      );
      const newCartItems = cartItems.filter(
        (item) => item.productId !== productId
      );
      setCartItems(newCartItems);
    } catch (error) {
      setError(error);
    }
  };

  if (error) {
    return <h1>Error:{error.message}</h1>;
  }

  if (loading) {
    return <h1>Loading...</h1>; //未取得購物車資料時顯示 Loading...
  }

  return (
    <div className="cart-container">
      <h1>購物車</h1>
      {cartItems.length === 0 ? (
        <p>購物車是空的</p>
      ) : (
        <>
          <ul className="cart-items">
            {cartItems.map((item) => (
              <li key={item.productId} className="cart-item">
                <button
                  onClick={() => handleDelete(item.productId)}
                  className="btn btn-danger delete-btn"
                >
                  刪除
                </button>
                <img
                  src={`/images/${item.product.prodId}.jpg`}
                  alt={item.product.prodName}
                  className="cart-item-image"
                />
                <div className="cart-item-info">
                  <h3>{item.product.prodName}</h3>
                  <p>數量: {item.quantity}</p>
                  <p>價格: ${item.product.prodPrice}</p>
                </div>
              </li>
            ))}
          </ul>
          <div className="cart-summary">
            <h3>總金額: ${totalAmount}</h3>
            <button onClick={handleCheckout} className="btn btn-primary">
              結帳
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default Cart;
