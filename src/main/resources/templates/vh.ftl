<VirtualHost *:80>
	ProxyPreserveHost On
	ProxyRequests Off

	ServerName www.${domainName}
	ServerAlias ${domainName}

	ProxyPass / http://${proxyAddress}:${proxyPort}/
    ProxyPassReverse / http://${proxyAddress}:${proxyPort}/

</VirtualHost>