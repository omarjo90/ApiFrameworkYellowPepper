import http from 'k6/http';  // Importing K6 HTTP module for making HTTP requests
import { check, sleep } from 'k6';  // Importing check and sleep functions from K6

// Define testing stages: ramp up, sustain, and ramp down load.
export let options = {
    stages: [
        { duration: '1m', target: 50 },  // Ramp-up to 50 users over 1 minute
        { duration: '3m', target: 50 },  // Sustain 50 users for 3 minutes
        { duration: '1m', target: 0 },   // Ramp-down to 0 users over 1 minute
    ],
    thresholds: {
        http_req_duration: ['p(95)<200'],  // 95% of requests should complete within 200ms
        http_req_failed: ['rate<0.01'],    // Less than 1% of requests should fail
        http_req_duration: ['avg<200'],     // Average request duration should be less than 200ms
        http_reqs: ['count>1000'],          // Total request count should exceed 1000
    }
};

// Define the payloads for POST and PUT requests.
const postPayload = JSON.stringify({
    id: 10,
    name: "doggie",
    category: { id: 1, name: "Dogs" },
    photoUrls: ["string"],
    tags: [{ id: 0, name: "string" }],
    status: "available"
});

const putPayload = JSON.stringify({
    id: 10,
    name: "UpdatedPet",
    category: { id: 1, name: "Dogs" },
    photoUrls: ["string"],
    tags: [{ id: 0, name: "string" }],
    status: "sold"
});

export default function () {
    // GET request to fetch pets by status
    let getResponse = http.get('http://localhost:8080/api/v3/pet/findByStatus?status=available');
    check(getResponse, {
        'GET /pet/findByStatus is status 200': (r) => r.status === 200,  // Check for HTTP status 200
        'GET /pet/findByStatus response time < 200ms': (r) => r.timings.duration < 200,  // Check response time
    });

    // POST request to add a new pet
    let postResponse = http.post('http://localhost:8080/api/v3/pet', postPayload, {
        headers: { 'Content-Type': 'application/json' },  // Set content type for JSON payload
    });
    check(postResponse, {
        'POST /pet is status 200': (r) => r.status === 200,  // Check for HTTP status 200
        'POST /pet response time < 200ms': (r) => r.timings.duration < 200,  // Check response time
    });

    // PUT request to update the pet
    let putResponse = http.put('http://localhost:8080/api/v3/pet', putPayload, {
        headers: { 'Content-Type': 'application/json' },  // Set content type for JSON payload
    });
    check(putResponse, {
        'PUT /pet is status 200': (r) => r.status === 200,  // Check for HTTP status 200
        'PUT /pet response time < 200ms': (r) => r.timings.duration < 200,  // Check response time
    });

    // DELETE request to remove a pet
    let deleteResponse = http.del('http://localhost:8080/api/v3/pet/10');
    check(deleteResponse, {
        'DELETE /pet is status 200': (r) => r.status === 200,  // Check for HTTP status 200
        'DELETE /pet response time < 200ms': (r) => r.timings.duration < 200,  // Check response time
    });

    sleep(1); // Simulate user wait time before the next iteration
}
