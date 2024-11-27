import React, { useState } from "react";
import { loginUser } from "../api/userApi.js";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const [username, setUsername] = useState("admin");
  const [password, setPassword] = useState("123456");
  const [errorMessage, setErrorMessage] = useState(""); // 新增錯誤訊息狀態
  const navigate = useNavigate(); // 使用 useNavigate hook

  const handleSubmit = async (e) => {
    e.preventDefault(); //避免表單提交後刷新頁面
    const response = await loginUser({ username, password });
    if (response.success) {
      localStorage.setItem("jwt", response.data.data); // 登入成功後將 token 存入 localStorage
      // console.log("登入成功", response.data);
      alert("登入成功!");
      navigate("/"); // 登入成功後導向首頁
    } else {
      // console.log("登入失敗", response.status);
      setErrorMessage(response.message); // 登入失敗的錯誤訊息
    }
  };
  return (
    <div style={{ maxWidth: "400px", margin: "auto", padding: "20px" }}>
      <h1>登入</h1>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "10px" }}>
          <label>帳號</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            style={{
              display: "block",
              width: "100%",
              padding: "8px",
              marginTop: "5px",
            }}
          />
        </div>
        <div style={{ marginBottom: "10px" }}>
          <label>密碼</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            style={{
              display: "block",
              width: "100%",
              padding: "8px",
              marginTop: "5px",
            }}
          />
        </div>
        <button
          type="submit"
          style={{
            width: "100%",
            padding: "10px",
            backgroundColor: "#4CAF50",
            color: "#fff",
          }}
        >
          登入
        </button>
      </form>

      {/* 錯誤訊息顯示框 */}
      {errorMessage && (
        <div
          style={{
            color: "#721c24",
            backgroundColor: "#f8d7da",
            border: "1px solid #f5c6cb",
            padding: "10px",
            marginTop: "15px",
            borderRadius: "5px",
            textAlign: "center",
          }}
        >
          {errorMessage}
        </div>
      )}
    </div>
  );
};
export default LoginPage;
