
---

# PetstorePerformanceTesting - Performance Test Metrics

The performance section utilize **K6**, a modern load testing tool. The K6 script defined in the project aims to evaluate the performance of various endpoints in the Swagger Petstore API by simulating different user load scenarios. It specifies testing stages to ramp up to 50 virtual users over a minute, sustain that load for three minutes, and then ramp down to zero users over the final minute. The script checks response status codes and response times, ensuring that 95% of requests complete within 200 milliseconds and that fewer than 1% of requests fail. This comprehensive approach allows for the thorough assessment of the API's performance and scalability under realistic user conditions.

## Performance Test Metrics

### Key Metrics

1. **http_req_duration**:
    - **Description**: This metric measures the time taken for HTTP requests to complete.
    - **Performance Targets**:
        - `p(95)<200`: 95% of requests should complete within 200 milliseconds.
        - `avg<200`: The average request duration should be less than 200 milliseconds.
    - **Interpretation**: A low `http_req_duration` indicates that the API responds quickly, enhancing user experience. Meeting the 95th percentile target is essential for ensuring that the majority of users have a smooth experience.

2. **http_req_failed**:
    - **Description**: This metric tracks the rate of failed HTTP requests.
    - **Performance Target**: `rate<0.01`: Less than 1% of requests should fail.
    - **Interpretation**: A failure rate exceeding 1% may indicate stability issues with the API. This metric is crucial for identifying potential bugs or service disruptions.

3. **http_reqs**:
    - **Description**: This metric counts the total number of HTTP requests made during the test.
    - **Performance Target**: `count>1000`: The total request count should exceed 1000.
    - **Interpretation**: A higher number of requests allows for a more robust performance evaluation, enabling the identification of trends and potential bottlenecks under load.

### Scenario Overview

- **Scenarios**:
    - **Description**: The test simulates a maximum of 50 virtual users (VUs) executing requests over a defined duration (5 minutes).
    - **Graceful Stop**: The scenario includes a graceful ramp-down period of 30 seconds to ensure that the system can handle the reduction in load smoothly.

### Results Summary

- **GET /pet/findByStatus**: Passed with a status code 200 and response time < 200ms.
- **POST /pet**: Failed for 1 out of 11,954 requests, resulting in a success rate of 99%.
- **PUT /pet**: Failed for 2,204 out of 11,955 requests, leading to an 81% success rate.
- **DELETE /pet**: Passed with a status code 200 and a failure rate of less than 1%.

### Performance Insights

- The average response time of 1.85 ms is significantly lower than the target of 200 ms, indicating that the API is responsive.
  - Why It Matters: In web applications, users typically expect a response within 200 ms. Keeping 95% of requests under this threshold ensures that the majority of users have a responsive experience. Exceeding this limit could indicate performance issues that might lead users to abandon the application.

- The failure rate of 4.62% for the `http_req_failed` metric suggests potential reliability issues that should be investigated further, especially since it exceeds the target of <1%.
  - Industry standards suggest that a failure rate below 1% is ideal for production systems. This is echoed by Amazon, which states that maintaining high availability and minimal errors is crucial for service reliability.

- The high count of total requests (47,820) demonstrates the robustness of the test and the ability of the API to handle significant load.
  - Load testing should ideally involve a significant number of requests to accurately simulate real-world usage patterns. Testing with over 1,000 requests is often considered a good practice to ensure that the API can handle expected traffic volumes efficiently

### Conclusion

The Maintaining these metrics within their specified thresholds is essential for delivering a high-quality API that meets user expectations and handles traffic efficiently. Regular performance testing against these benchmarks will help ensure the reliability, speed, and scalability of your application.

---