import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  useLocation,
} from "react-router-dom";
import Navbar from "./components/Navbar.js";
import Home from "./pages/Home.js";
import LoginPage from "./pages/LoginPage.js";
import RegisterPage from "./pages/RegisterPage.js";
import ProductDetailsPage from "./pages/ProductDetailPage.js";
import ProductsPage from "./pages/ProductsPage.js";
import Cart from "./pages/Cart.js";
import "./styles/global.css";

// 新增 AppContent，將 Router 元件內容封裝到 AppContent 中
const App = () => {
  return (
    <Router>
      <AppContent />
    </Router>
  );
};
//如果路徑為 /login 或 /register，則不顯示 Navbar
const AppContent = () => {
  const location = useLocation();
  const hideNavbar =
    location.pathname === "/login" || location.pathname === "/register";

  return (
    <>
      {!hideNavbar && <Navbar />}
      <div className="bg-primary text-white p-5">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/products" element={<ProductsPage />} />
          <Route path="/products/:id" element={<ProductDetailsPage />} />
          <Route path="/cart/:userId" element={<Cart />} />
        </Routes>
      </div>
    </>
  );
};

export default App;
