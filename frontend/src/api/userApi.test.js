// src/api/userApi.test.js
import axios from "axios";
import { registerUser, loginUser } from "./userApi";

jest.mock("axios");

describe("userApi", () => {
  describe("registerUser", () => {
    it("should register a user successfully", async () => {
      const userData = { username: "testuser", password: "password" };
      const responseData = { id: 1, username: "testuser" };

      axios.post.mockResolvedValue({ status: 201, data: responseData });

      const result = await registerUser(userData);

      expect(result).toEqual({ success: true, data: responseData });
      expect(axios.post).toHaveBeenCalledWith(
        "http://localhost:8080/api/user/register",
        userData
      );
    });

    it("should return an error message if the user is already registered", async () => {
      const userData = { username: "testuser", password: "password" };
      const errorMessage = "註冊失敗，帳號或信箱已註冊過。";

      axios.post.mockRejectedValue({
        response: { status: 409, data: { msg: errorMessage } },
      });

      const result = await registerUser(userData);

      expect(result).toEqual({ success: false, message: errorMessage });
    });

    it("should return a generic error message if registration fails", async () => {
      const userData = { username: "testuser", password: "password" };

      axios.post.mockRejectedValue(new Error("Network Error"));

      const result = await registerUser(userData);

      expect(result).toEqual({
        success: false,
        message: "註冊失敗，請確認連線。",
      });
    });
  });

  describe("loginUser", () => {
    it("should login a user successfully", async () => {
      const userData = { username: "testuser", password: "password" };
      const responseData = { token: "fake-jwt-token" };

      axios.post.mockResolvedValue({ status: 200, data: responseData });

      const result = await loginUser(userData);

      expect(result).toEqual({ success: true, data: responseData });
      expect(axios.post).toHaveBeenCalledWith(
        "http://localhost:8080/api/user/login",
        userData,
        {
          headers: { "Content-Type": "application/json" },
        }
      );
    });

    it("should return an error message if the credentials are incorrect", async () => {
      const userData = { username: "testuser", password: "wrongpassword" };
      const errorMessage = "帳號或密碼錯誤";

      axios.post.mockRejectedValue({
        response: { status: 401, data: { msg: errorMessage } },
      });

      const result = await loginUser(userData);

      expect(result).toEqual({ success: false, message: errorMessage });
    });

    it("should return a generic error message if login fails", async () => {
      const userData = { username: "testuser", password: "password" };

      axios.post.mockRejectedValue(new Error("Network Error"));

      const result = await loginUser(userData);

      expect(result).toEqual({
        success: false,
        message: "登入失敗，請確認連線。",
      });
    });
  });
});
