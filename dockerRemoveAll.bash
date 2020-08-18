# shellcheck disable=SC2046
docker rm -f $(docker ps -qa)
docker rmi ktor-test-case:latest