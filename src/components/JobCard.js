import React from "react";
import { applyJob, saveJob, unsaveJob } from "../services/api";
import "../styles/JobCard.css";

const JobCard = ({ job, token }) => {
  const handleApply = async () => {
    try {
      await applyJob(job.id, token);
      alert("Applied successfully!");
    } catch (err) {
      alert("Failed to apply.");
    }
  };

  const handleSave = async () => {
    try {
      await saveJob(job.id, token);
      alert("Job saved!");
    } catch (err) {
      alert("Failed to save job.");
    }
  };

  const handleUnsave = async () => {
    try {
      await unsaveJob(job.id, token);
      alert("Job unsaved!");
    } catch (err) {
      alert("Failed to unsave job.");
    }
  };

  return (
    <div className="job-card">
      <h3>{job.title}</h3>
      <p>{job.description}</p>
      <p><strong>{job.company}</strong> | {job.location} | {job.type}</p>
      <p>Salary: {job.salary}</p>

      <div className="button-group">
        <button onClick={handleApply}>Apply</button>
        <button onClick={handleSave}>Save</button>
        <button onClick={handleUnsave}>Unsave</button>
      </div>
    </div>
  );
};

export default JobCard;
