# Docker and Vagrant Example

This repository illustrates running multiple containers in a single host using Vagrant. The idea is that with just Vagrant and Virtualbox, a new developer could checkout this repo and run `vagrant up` and be ready to start contributing without having to mess with a ton of environment setup. What makes this project unique is that it simulates a more complicated architecture than just a straight up web server and database.

View deck.pptx for more detailed information about this setup.

## Running the Application

1. Install Vagrant: https://www.vagrantup.com/downloads.html
1. Install Virtualbox: https://www.virtualbox.org/wiki/Downloads
1. Checkout this repo
1. run `vagrant up`

### Services that are deployed

1. `lb`: An **apache** instance with *mod_proxy* installed. This redirects RESTful API requests to the appropriate micro service.
1. `order`: A **Spring Boot** micro service to handle creating and viewing pizza orders
1. `kitchen`: A **Spring Boot** micro service to manage the order queue for the pizza cooks
1. `delivery`: A **Spring Boot** micro service to manage pizza order deliveries
1. `messages`: A **RabbitMQ** instance to handle messaging between services

### API Reference

The following "happy path" workflow demonstrates the available APIs and their use. The response of all API calls is JSON with the following structure:

```
{
  message: <String>,
  isError: <true|false>,
  error: <Java Exception Object> (only present if isError is true),
  itemType: <String> (what object type is returned. In this example usually Order or Long),
  item: <JSON of instance of type returned> (only present for single object actions),
  items: <Array of item instances> (only present for list actions)
}
```

Action                  |Method|URL                                        |Description                      |Arguments
---|---|---|---|---
List Orders             |GET   |http://10.10.10.29/order/                  |Lists all orders                 |
Create Order            |POST  |http://10.10.10.29/order/                  |Create a new order               |`{`<br>&nbsp;&nbsp;`"customerName": "Scott Stevenson",`<br>&nbsp;&nbsp;`"customerEmail": "sstevenson@credera.com",`<br>&nbsp;&nbsp;`"customerPhone": "713-555-1234",`<br>&nbsp;&nbsp;`"orderTotal": 24.65`<br>&nbsp;&nbsp;`}`
View Order              |GET   |http://10.10.10.29/order/{id}              |Retrieve single order by ID      |
Update Order            |PUT   |http://10.10.10.29/order/{id}              |                                 |`{`<br>&nbsp;&nbsp;`"customerName": "Scott Stevenson",`<br>&nbsp;&nbsp;`"customerEmail": "sstevenson@credera.com",`<br>&nbsp;&nbsp;`"customerPhone": "713-555-1234",`<br>&nbsp;&nbsp;`"orderTotal": 24.65`<br>&nbsp;&nbsp;`}`
Kitchen Queue Size      |GET   |http://10.10.10.29/kitchen/pendingCount    |Number of orders to bake         |
Kitchen Processing Size |GET   |http://10.10.10.29/kitchen/processingCount |Number of orders baking          |
Kitchen Next            |GET   |http://10.10.10.29/kitchen/next            |Pull next order to bake          |
Kitchen Complete        |PUT   |http://10.10.10.29/kitchen/complete/{id}   |Mark as baked, ready for delivery|
Delivery Queue Size     |GET   |http://10.10.10.29/delivery/pendingCount   |Number of orders to deliver      |
Delivery Processing Size|GET   |http://10.10.10.29/delivery/processingCount|Number of orders out for delivery|
Delivery Next           |GET   |http://10.10.10.29/delivery/next           |Pull next order to deliver       |
Delivery Complete       |PUT   |http://10.10.10.29/delivery/complete/{id}  |Mark as complete                 |

The default IP of the application is 10.10.10.29. This can be modified in the `host/Vagrantfile`

## Development

1. Use IDE of choice to modify source files on your local system
1. Redeploy service by running `vagrant reload <service name>`
