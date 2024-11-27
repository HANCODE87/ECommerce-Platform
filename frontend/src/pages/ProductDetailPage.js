import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../styles/productDetailPage.css";
import { jwtDecode } from "jwt-decode";
import { getProductById } from "../api/productApi.js";
import axiosInstance from "../api/axiosInstance.js";

const ProductDetailPage = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const navigate = useNavigate();

  // 根據 id 取得商品資料
  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await getProductById(id);
        setProduct(response);
      } catch (error) {
        console.error(error);
      }
    };
    fetchProduct();
  }, [id]); // id 改變時重新取得商品資料

  // 如果商品資料還沒載入完成，則顯示 Loading...
  if (!product) {
    return <h1>Loading...</h1>;
  }

  // 增加商品數量
  const increaseQuantity = () => {
    setQuantity(quantity + 1);
  };

  // 減少商品數量
  const decreaseQuantity = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  // 加入購物車
  const addToCart = async () => {
    // 確認是否登入
    const token = localStorage.getItem("jwt");
    if (!token) {
      alert("請先登入");
      navigate("/login");
      return;
    }

    // 加入購物車，在後端新增訂單
    try {
      const decodedToken = jwtDecode(token);
      const userId = decodedToken.userId; // 提取 userId
      const response = await axiosInstance.post(
        `http://localhost:8080/api/orders`,
        {
          userId,
          productId: product.prodId,
          quantity,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.status === 201) {
        alert("加入購物車成功");
      }
    } catch (error) {
      alert("加入購物車失敗，請稍後再試。");
      console.error("加入購物車失敗:", error);
    }
  };

  return (
    <div className="product-detail">
      <div className="product-image">
        <img src={`/images/${product.prodId}.jpg`} alt={product.prodName} />
      </div>
      <div className="product-info">
        <h1>{product.prodName}</h1>
        <p className="product-price">價格: ${product.prodPrice}</p>
        <div className="quantity-selector">
          <button onClick={decreaseQuantity} className="quantity-btn">
            -
          </button>
          <span className="quantity">{quantity}</span>
          <button onClick={increaseQuantity} className="quantity-btn">
            +
          </button>
        </div>
        <button onClick={addToCart} className="btn btn-primary add-to-cart">
          加入購物車
        </button>
      </div>
    </div>
  );
};

export default ProductDetailPage;
