import React, { useState } from "react";
import { searchJobs } from "../services/api";
import JobCard from "./JobCard";

import "./JobSearch.css"; // Create this CSS file

const JobSearch = () => {
  const [formData, setFormData] = useState({
    keyword: "",
    location: "",
    type: "",
  });

  const [jobs, setJobs] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const token = localStorage.getItem("token");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSearch = async () => {
    try {
      setLoading(true);
      const response = await searchJobs(formData, token);
      setJobs(response.data.content);
    } catch (err) {
      setError("Failed to fetch jobs");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="job-search-container">
      <h2 className="title">Search Jobs</h2>
      <div className="search-form">
        <input
          type="text"
          name="keyword"
          placeholder="Keyword"
          value={formData.keyword}
          onChange={handleChange}
        />
        <input
          type="text"
          name="location"
          placeholder="Location"
          value={formData.location}
          onChange={handleChange}
        />
        <input
          type="text"
          name="type"
          placeholder="Type (e.g. full-time)"
          value={formData.type}
          onChange={handleChange}
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}

      <div className="job-list">
  {jobs.map((job) => (
    <JobCard key={job.id} job={job} token={token} />
  ))}
</div>

    </div>
  );
};

export default JobSearch; 

