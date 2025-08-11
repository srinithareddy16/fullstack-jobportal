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

export const applyJob = async (jobId, token) => {
  return api.post(`/jobs/${jobId}/apply`, {}, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

export const saveJob = async (jobId, token) => {
  return api.post(`/jobs/${jobId}/save`, {}, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

export const unsaveJob = async (jobId, token) => {
  return api.delete(`/jobs/${jobId}/unsave`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};