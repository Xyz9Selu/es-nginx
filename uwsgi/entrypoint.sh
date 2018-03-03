#!/bin/bash

sed -i 's,%{UWSGI_URL}%,'"${UWSGI_URL}"',g' /etc/nginx/conf.d/service.conf \
&& nginx -g "daemon off;"
