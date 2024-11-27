// src/api/productApi.test.js
import axios from "axios";
import {
  getPopularProducts,
  getProductById,
  getProductsByKeyWord,
} from "./productApi";

jest.mock("axios");

describe("productApi", () => {
  let consoleErrorSpy;

  beforeAll(() => {
    consoleErrorSpy = jest.spyOn(console, "error").mockImplementation(() => {});
  });

  afterAll(() => {
    consoleErrorSpy.mockRestore();
  });

  describe("getPopularProducts", () => {
    it("should fetch popular products successfully", async () => {
      const responseData = {
        data: [
          { id: 1, name: "Product1" },
          { id: 2, name: "Product2" },
        ],
      };

      axios.get.mockResolvedValue({ data: responseData });

      const result = await getPopularProducts();

      expect(result).toEqual(responseData.data);
      expect(axios.get).toHaveBeenCalledWith(
        "http://localhost:8080/api/products/popular"
      );
    });

    it("should throw an error if fetching popular products fails", async () => {
      axios.get.mockRejectedValue(new Error("Network Error"));

      await expect(getPopularProducts()).rejects.toThrow("Network Error");
    });
  });

  describe("getProductById", () => {
    it("should fetch product by id successfully", async () => {
      const productId = 1;
      const responseData = { data: { id: 1, name: "Product1" } };

      axios.get.mockResolvedValue({ data: responseData });

      const result = await getProductById(productId);

      expect(result).toEqual(responseData.data);
      expect(axios.get).toHaveBeenCalledWith(
        `http://localhost:8080/api/products/${productId}`
      );
    });

    it("should throw an error if fetching product by id fails", async () => {
      const productId = 1;

      axios.get.mockRejectedValue(new Error("Network Error"));

      await expect(getProductById(productId)).rejects.toThrow("Network Error");
    });
  });

  describe("getProductsByKeyWord", () => {
    it("should fetch products by keyword successfully", async () => {
      const keyWord = "test";
      const page = 1;
      const pageSize = 10;
      const responseData = {
        data: [
          { id: 1, name: "Product1" },
          { id: 2, name: "Product2" },
        ],
      };

      axios.get.mockResolvedValue({ data: responseData });

      const result = await getProductsByKeyWord(keyWord, page, pageSize);

      expect(result).toEqual(responseData.data);
      expect(axios.get).toHaveBeenCalledWith(
        `http://localhost:8080/api/products/search?keyWord=${keyWord}&page=${page}&pageSize=${pageSize}`
      );
    });

    it("should throw an error if fetching products by keyword fails", async () => {
      const keyWord = "test";
      const page = 1;
      const pageSize = 10;

      axios.get.mockRejectedValue(new Error("Network Error"));

      await expect(
        getProductsByKeyWord(keyWord, page, pageSize)
      ).rejects.toThrow("Network Error");
    });
  });
});
