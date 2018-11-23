#!/bin/bash

cp /etc/nginx/conf.d/service.conf.template /etc/nginx/conf.d/service.conf \
&& sed -i 's,%{UWSGI_URL}%,'"${UWSGI_URL}"',g' /etc/nginx/conf.d/service.conf \
&& cat /etc/nginx/conf.d/service.conf \
&& nginx -g "daemon off;"
