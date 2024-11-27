import React, { useState, useEffect } from "react";
import ProductItem from "../components/ProductItem.js";
import { getSortProducts, getProductsByKeyWord } from "../api/productApi.js";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/productsPage.css";

function ProductsPage() {
  const [products, setProducts] = useState([]);
  const [sortField, setSortField] = useState("name");
  const [isAsc, setIsAsc] = useState(true);
  const [searchKeyWord, setSearchKeyWord] = useState("");

  useEffect(() => {
    const fetechProduct = async () => {
      try {
        //預設為依照商品名稱正序排序
        const data = await getSortProducts(sortField, isAsc);
        setProducts(data);
      } catch (error) {
        console.log(error);
      }
    };
    //改變 sortField 或 isAsc 時重新取得商品資料
    fetechProduct();
  }, [sortField, isAsc]);

  //設定搜尋欄位
  const handleSearch = async () => {
    try {
      const data = await getProductsByKeyWord(searchKeyWord, sortField, isAsc);
      setProducts(data);
    } catch (error) {
      console.log(error);
    }
  };
  //如果商品列表為空，顯示 Loading...
  if (products.length === 0) {
    return <h1>Loading...</h1>;
  }

  return (
    <div className="container">
      <h1>產品列表</h1>
      <div className="row mb-3">
        <div className="col-md-8">
          <div className="btn-group me-2" role="group">
            <button
              className={`btn btn-primary ${
                sortField === "name" ? "active-sort" : ""
              }`}
              onClick={() => setSortField("name")}
            >
              名稱排序
            </button>
            <button
              className={`btn btn-primary ${
                sortField === "price" ? "active-sort" : ""
              }`}
              onClick={() => setSortField("price")}
            >
              價格排序
            </button>
            <button
              className={`btn btn-primary ${
                sortField === "sales" ? "active-sort" : ""
              }`}
              onClick={() => setSortField("sales")}
            >
              銷量排序
            </button>
          </div>
          <div className="btn-group" role="group">
            <button
              className={`btn btn-secondary ${
                isAsc ? "active-sort-order" : ""
              }`}
              onClick={() => setIsAsc(true)}
            >
              正序
            </button>
            <button
              className={`btn btn-secondary ${
                !isAsc ? "active-sort-order" : ""
              }`}
              onClick={() => setIsAsc(false)}
            >
              倒序
            </button>
          </div>
        </div>
        <div className="col-md-4">
          <div className="input-group">
            <input
              type="text"
              className="form-control"
              placeholder="搜尋商品"
              value={searchKeyWord}
              onChange={(e) => setSearchKeyWord(e.target.value)}
            />
            <button className="btn btn-primary" onClick={handleSearch}>
              搜尋
            </button>
          </div>
        </div>
      </div>
      <div className="row">
        {products.map((product) => (
          <div className="col-md-2" key={product.prodId}>
            <ProductItem product={product} />
          </div>
        ))}
      </div>
    </div>
  );
}

export default ProductsPage;
