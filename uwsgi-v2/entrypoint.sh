#!/bin/bash

cp /etc/nginx/conf.d/service.conf.template /etc/nginx/conf.d/service.conf \
&& sed -i 's,%{UPSTREAM_UWSGI_SERVICES}%,'"${UPSTREAM_UWSGI_SERVICES}"',g' /etc/nginx/conf.d/service.conf \
&& cat /etc/nginx/conf.d/service.conf \
&& nginx -g "daemon off;"
