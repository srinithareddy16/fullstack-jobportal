import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

const api = axios.create({
  baseURL: BASE_URL,
});

export const searchJobs = async (params, token) => {
  return api.get("/jobs/search", {
    params,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};
