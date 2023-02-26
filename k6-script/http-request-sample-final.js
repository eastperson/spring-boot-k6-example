import { check, group, sleep } from "k6";
import http from "k6/http";
export let options = {
    max_vus: 100,
    vus: 100,
    stages: [
        { duration: "1m", target: 30 },
        { duration: "2m", target: 40 },
        { duration: "1m", target: 50 },
    ],
};
export default function () {
    group("hello-api-test", function () {
        group("get request", function () {
            let res = http.get(
                "http://host.docker.internal:8080/hello?name=ep"
            );
            check(res, { "status is 200": (r) => r.status === 200 });
            sleep(1);
        });
        group("post request", function () {
            let res = http.post("http://host.docker.internal:8080/hello");
            check(res, { "status is 200": (r) => r.status === 200 });
            sleep(2);
        });
        group("put request", function () {
            let res = http.put("http://host.docker.internal:8080/hello/1");
            check(res, { "status is 200": (r) => r.status === 200 });
            sleep(3);
        });
        group("delete request", function () {
            let res = http.del("http://host.docker.internal:8080/hello/1");
            check(res, { "status is 200": (r) => r.status === 200 });
            sleep(4);
        });
    });
}