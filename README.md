# Spring cloud aws sample

This is a sample application for demonstration purposes only. It is a very simple application devoted to managing employee names. The idea is demonstrate Spring Cloud AWS capabilities. In order to run it, you need a minimum AWS configuration (see below).

The application allows users to create employee records. Employee records are stored in a RDS database in AWS. The project is configured to use PostgreSQL, but that can be changed to use a different database (for instance, MySQL).

## Prerequisites

### Create an RDS Instance

**WARNING**: These instructions allow you to run and test the application from within your development environment (i.e., without deploying it to AWS) using an RDS instance open to the world, which is something you should avoid in production.

First, create a _security group_ (i.e. springboot-postgres-sg) that will be used to allow ingress connections from outside AWS. Whithin the security group just created, create a new _access rule_ with the following configuration:

* Type: PostgreSQL
* Source: Anywhere
* CIDR: 0.0.0.0/0

Then, create an RDS instance, with these properties:

* Engine type PostgreSQL
* Version PostgreSQL 11.9-R1
* Type Free Tier
* DB Instance identifier employeedb
* DB Instance Class: t2.micro
* Storage General Purpose (SSD), allocated storage 20 GiB
* Multizone AZ: no
* Master username: postgres
* Master password: postgres
* Ensure Default VPC is enabled
* Ensure Publicly accessible is yes
* VPC security group: choose the security group previously created (i.e. springboot-postgres-sg)
* Database name: employeedb
* Launch!!

#### Create an IAM User

- Enable programmatic access
- Generate an access key for the user
- Give the user the following permissions:
	- AmazonS3FullAccess
	- AmazonRDSFullAccess

### To run on EC2

#### Create an IAM role

Create an IAM role with the following properties:

- EC2 role (i.e., a role to be attached to EC2 instances)
- Policies:
	- AmazonRDSFullAccess

**WARNING**: It's a good practice to limit policies and not give all available permissions. We're selecting FullAccess as a proof of concept. Real deployment environments must have more restrictive policies.

#### Create an EC2 instance

It has been tested with an instance with the following properties:

* AMI: Ubuntu 18.04
* Type: t2.micro
* Storage: 20Gb
* Security group: choose or create one with ports 22 and 8080 opened
* Attach the IAM role created previously

Once the instance has been started, ssh'd into the machine and issue the following commands:

```
sudo apt-get update
sudo apt-get install openjdk-8-jre-headless
```

Then from your own machine, build the jar file and upload it to your EC2 instance:

```
mvn package -DskipTests
scp -i <your key> springboot-postgres-aws-0.0.1-SNAPSHOT.jar ubuntu@<your ec2 ip>:/home/ubuntu/
```

### On AWS (prod)

If your EC2 instance has the appropriate role (see prerequisites above), and the jar file has been uploaded, and you have created your Secret

    java -jar springboot-postgres-aws-0.0.1-SNAPSHOT.jar


See:
https://www.briansdevblog.com/2016/05/spring-boot-amazon-web-services-ec2-rds-s3/
https://github.com/codeurjc/spring-cloud-aws-sample