import React from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/productItem.css"; // 導入自定義 CSS

//製做一個products component並且展示商品圖片、商品名稱、商品價格
function ProductItem({ product }) {
  return (
    <div className="card product-item">
      <img
        src={`/images/${product.prodId}.jpg`}
        className="card-img-top"
        alt={product.prodName}
      />
      <div className="card-body">
        <h5 className="card-title">{product.prodName}</h5>
        <p className="card-text">銷量: {product.sales}</p>
        <p className="card-text">價格: ${product.prodPrice}</p>
        <p className="card-text">數量: {product.prodStock}</p>
        <Link to={`/products/${product.prodId}`} className="btn btn-primary">
          詳細資訊
        </Link>
      </div>
    </div>
  );
}

export default ProductItem;
