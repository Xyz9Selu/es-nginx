# d-proxy

定向转发
ENV TARGET_HOST='your.host.com'
ENV TARGET_URL='http(s)://your.host.com/your/path'


# http

HTTP反向代理
ENV UPSTREAM_SERVICES='server your.server1;server your.server2;'


# uwsgi

uwsgi反向代理
ENV UWSGI_URL=your.service.hostname:port


# uwsgi-v2

uwsgi反向代理
ENV UPSTREAM_UWSGI_SERVICES='server your.site.host:port;server unix:///path/to/your/site/site.sock;'
