FROM nginx

# http upstream services
# server your.server1;
# server your.server2;
# ...
ENV ROOT_PATH='/var/www/html'
ENV URL_PATH='/'

# set timezone
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# install network analyse tools
# RUN apt-get update && apt-get install -y dnsutils

RUN rm /etc/nginx/conf.d/default.conf
COPY ./conf.d /etc/nginx/conf.d
COPY ./nginx.conf /etc/nginx/nginx.conf
COPY ./proxy_params /etc/nginx/proxy_params
COPY ./entrypoint.sh /entrypoint.sh

# ENTRYPOINT ["/entrypoint.sh"]
CMD ["sh", "/entrypoint.sh"]
