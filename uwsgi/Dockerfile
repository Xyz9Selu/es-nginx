FROM registry.docker-cn.com/library/nginx:latest

# uwsgi url, 
# tcp port: ip:port
# sock file: unix://your/sock/file.sock
ENV UWSGI_URL=your.service.hostname:port

# set timezone
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# install network analyse tools
# RUN apt-get update && apt-get install -y dnsutils

RUN rm /etc/nginx/conf.d/default.conf
COPY ./conf.d /etc/nginx/conf.d
COPY ./nginx.conf /etc/nginx/nginx.conf
COPY ./entrypoint.sh /entrypoint.sh

# ENTRYPOINT ["/entrypoint.sh"]
CMD ["sh", "/entrypoint.sh"]
