import axios from "axios";

//創建一個 axios 實例
const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api",
  withCredentials: true,
});
//配置攔截器使其在每次請求時攜帶 Authorization標頭
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("jwt");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
      console.log(config);
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
//如果請求返回 401 或 403 狀態碼，則清除 token 並返回錯誤
axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    // 檢查錯誤響應
    if (error.response) {
      console.log("Error Status:", error.response.status);
      console.log("Error Data:", error.response.data);

      if (error.response.status === 403) {
        alert("登入逾時，請重新登入。");
        localStorage.removeItem("jwt");
        // 如果需要重定向到登錄頁面
        window.location.href = "/login";
      }

      if (error.response.status === 401) {
        alert("登入驗證有誤，請重新登入。");
        localStorage.removeItem("jwt");
        window.location.href = "/login";
      }
    }

    return Promise.reject(error);
  }
);

export default axiosInstance;
