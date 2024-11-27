import React from "react";
import { registerUser } from "../api/userApi.js";
import { useNavigate } from "react-router-dom";

const RegisterPage = () => {
  const [username, setUsername] = React.useState("admin");
  const [password, setPassword] = React.useState("123456");
  const [email, setEmail] = React.useState("4@4");
  const [errorMessage, setErrorMessage] = React.useState(""); // 新增錯誤訊息狀態
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault(); //避免表單提交後刷新頁面
    const response = await registerUser({ username, password, email });
    if (response.success) {
      // console.log("註冊成功", response.data);
      alert("註冊成功，請登入。");
      navigate("/login"); // 註冊成功後導向登入頁
    } else {
      setErrorMessage(response.message); // 註冊失敗的錯誤訊息
      // console.log("註冊失敗", response.status);
      // alert(response.message); // 註冊失敗的錯誤訊息
    }
  };

  return (
    <div>
      <h1>註冊</h1>
      {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}{" "}
      {/* 顯示錯誤訊息 */}
      <form onSubmit={handleSubmit}>
        <div>
          <label>帳號</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div>
          <label>密碼</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div>
          <label>電子信箱</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <button type="submit">註冊</button>
      </form>
    </div>
  );
};

export default RegisterPage;
