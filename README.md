# adv

For starting, you should :

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Start with default properties

    - clone project to your own computer

    - in terminal- >

    - go to the project directory

    - enter comand
        ' sh start.sh '

By default access to Database -> 'http://localhost:8080/h2-console'

For starting with your file - 

     - java -jar ./target/sponsoredAds-0.0.1-SNAPSHOT.jar --file.link=${link to your file}

For starting with your port -

     - java -jar ./target/sponsoredAds-0.0.1-SNAPSHOT.jar --server.port=${your port}
 
Exceptions -> 

    - HttpStatus.INTERNAL_SERVER_ERROR - 500
    - HttpStatus.BAD_REQUEST = - 400 Errors of Validation
    - HttpStatus.CONFLICT - 409 - CampaignName not unique


REQUEST JSON For createCampaign API -> 

    {
    "campaignTitle":"SuperPuperCampaign",
    "startDate":"{{currentDate}}",
    "bid":3.6,
    "category":"PHONE"
    }


