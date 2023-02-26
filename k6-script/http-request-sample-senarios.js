import http from "k6/http";
import { check } from "k6";
export const options = {
    scenarios: {
        shared_iter_scenario: {
            executor: "shared-iterations",
            vus: 10,
            iterations: 2000,
            startTime: "0s",
        },
        per_vu_scenario: {
            executor: "per-vu-iterations",
            vus: 10,
            iterations: 200,
            startTime: "1m30s",
        },
    },
};
export default function () {
    let res = http.get("https://test.k6.io/contacts.php");
    check(res, {
        "is status 200": (r) => r.status === 200,
    });
}