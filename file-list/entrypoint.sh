#!/bin/bash

cp /etc/nginx/conf.d/service.conf.template /etc/nginx/conf.d/service.conf \
&& sed -i 's,%{ROOT_PATH}%,'"${ROOT_PATH}"',g' /etc/nginx/conf.d/service.conf \
&& sed -i 's,%{URL_PATH}%,'"${URL_PATH}"',g' /etc/nginx/conf.d/service.conf \
&& cat /etc/nginx/conf.d/service.conf \
&& nginx -g "daemon off;"
