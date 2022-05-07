#!/bin/bash

sudo mkdir -p /var/www/workbyte.in/public_html

sudo chown -R $USER:$USER /var/www/workbyte.in/public_html

sudo chmod -R 755 /var/www

sudo cp workbyte.in.conf /etc/apache2/sites-available/workbyte.in.conf

sudo a2ensite workbyte.in.conf

sudo /etc/init.d/apache2 restart
