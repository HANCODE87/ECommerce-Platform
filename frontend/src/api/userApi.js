import axios from "axios";
const API_URL = "http://localhost:8080/api/user";

//註冊用戶
export const registerUser = async (userData) => {
  try {
    const response = await axios.post(`${API_URL}/register`, userData);
    if (response.status === 201) {
      return { success: true, data: response.data };
    }
  } catch (error) {
    if (error.response && error.response.status === 409) {
      return {
        success: false,
        message: error.response.data.msg || "註冊失敗，帳號或信箱已註冊過。",
      };
    }
    return { success: false, message: "註冊失敗，請確認連線。" };
  }
};

//登錄用戶
export const loginUser = async (userData) => {
  try {
    const response = await axios.post(`${API_URL}/login`, userData, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    // 狀態碼處理
    if (response.status === 200) {
      return { success: true, data: response.data };
    }
  } catch (error) {
    //確認是否有回應並且回應的是401錯誤
    if (error.response && error.response.status === 401) {
      return {
        success: false,
        message: error.response.data.msg || "帳號或密碼錯誤",
      };
    } else {
      return { success: false, message: "登入失敗，請確認連線。" };
    }
  }
};
