// src/services/axiosConfig.js
import axios from "axios";
import { getToken } from "./AuthService";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});

apiClient.interceptors.request.use((config) => {
  const token = getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default apiClient;
