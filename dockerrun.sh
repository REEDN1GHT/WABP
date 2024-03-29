docker build -f DockerfileGhrome -t kf .
docker run -it --rm -v .:/app kf bash