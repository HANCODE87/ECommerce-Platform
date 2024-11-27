import axios from "axios";

const API_URL = "http://localhost:8080/api/products";

export const getSortProducts = async (sortField, isAsc) => {
  try {
    const response = await axios.get(
      `${API_URL}/sort?sort=${sortField}&isAsc=${isAsc}`
    );
    return response.data.data;
  } catch (error) {
    console.error("獲取產品失敗:", error);
    throw error;
  }
};

export const getProductById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data.data;
  } catch (error) {
    console.error("獲取產品失敗:", error);
    throw error;
  }
};

export const getProductsByKeyWord = async (keyWord, sortField, isAsc) => {
  try {
    const response = await axios.get(
      `${API_URL}/search?keyWord=${keyWord}&sort=${sortField}&isAsc=${isAsc}`
    );
    return response.data.data;
  } catch (error) {
    console.error("獲取產品失敗:", error);
    throw error;
  }
};
