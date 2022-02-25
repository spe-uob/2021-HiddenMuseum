# Example Deployment with Nginx on a Linux Server

Here's an example to follow along with that will show you how to set up the Hidden Museum web app behind an Nginx reverse proxy. Nginx will act as the web server (and can also be set up to handle SSL and HTTPS redirects) while the web app will be proxied to it, not being directly exposed to the internet.

**Important:** This guide assumes you are accessing your server machine in order to run the web app on it. You will need to either be SSH'd into the machine (e.g. with PuTTY or some other SSH client), or have physical access to it.

## 1. Install Nginx

On Ubuntu/Debian
```sh
$ sudo apt update
$ sudo apt install nginx
```

For details on how to install Nginx on other operating systems, see [the Nginx documentation](http://nginx.org/en/docs/install.html).

To make Nginx automatically start on system boot, you can use SystemD.
```sh
$ sudo systemctl enable nginx
$ sudo systemctl start nginx
```

If your operating system uses a different service manager, look up appropriate manuals.

## 2. Install Docker

On Ubuntu/Debian
```sh
$ sudo apt install docker
```

For details on how to install Docker on other operating systems, see [Docker's documentation](https://docs.docker.com/get-docker/).

Once again, you can use SystemD to automatically start Docker on system boot.
```sh
$ sudo systemctl enable docker
$ sudo systemctl start docker
```

## 3. Install and Run the Hidden Museum Web App

```sh
$ docker pull ghcr.io/spe-uob/2021-hiddenmuseum:dev
```

We'll be running the web app on port 8080, with SSL disabled. The `--restart always` flag means Docker will automatically restart this container, unless if it is stopped manually.

```sh
$ docker run -d --restart always -p 8080:8080 ghcr.io/spe-uob/2021-hiddenmuseum:dev . --server.port=8080 --server.ssl.enabled=false
```

Now, the web app is running. All that is required is a way to access it.

## 4. Configure the Reverse Proxy

By default, Nginx is configured to serve static files. However, what we want is to set it so that it serves our web app.

First, we disable the current virtual host
```sh
$ sudo unlink /etc/nginx/sites-enabled/default
```

Next, we create a new virtual host for our web app, using a text editor of your choice
```sh
$ sudo vim /etc/nginx/sites-available/hiddenmuseum.conf
```

Add the following to the file
```conf
server {
	listen 80;
	listen [::]:80;

	location / {
		proxy_pass http://localhost:8080;
	}
}
```

For more information on configuring Nginx, see [the Nginx documentation](http://nginx.org/en/docs/beginners_guide.html).

In order to enable this virtual host, we create a symlink
```sh
$ sudo ln -s /etc/nginx/sites-available/hiddenmuseum.conf /etc/nginx/sites-enabled/hiddenmuseum.conf
```

We can then restart the Nginx service, and the web app should be accessible in a browser, using either the server's IP address, or with a configured domain name.
```sh
$ sudo systemctl restart nginx
```

## 5. Installing SSL Certificates (Recommended)

*This part is under construction, and will be written at a later date. In the meantime, see the [Nginx docs](https://docs.nginx.com/nginx/admin-guide/security-controls/terminating-ssl-http/). If you need free, open source, automatically renewable CA-signed SSL certificates, check out [Let's Encrypt](https://letsencrypt.org/), a non-profit aiming to make HTTPS more accessible.*
