#!/bin/bash

cp /etc/nginx/conf.d/service.conf.template /etc/nginx/conf.d/service.conf \
&& sed -i 's,%{HTTP_URL}%,'"${HTTP_URL}"',g' /etc/nginx/conf.d/service.conf \
&& nginx -g "daemon off;"
