# Software Engineering DevOps Test

## Goal

Availability of *Garmin Pay's* Restful APIs is of utmost importance to our
business model. While incredibly rare, service outages do occur which require
immediate action from support staff. We need tooling to monitor the health of
these APIs and inform the appropriate individuals when things go awry.

### Technology Assumptions

* Technology choice is whatever **scripting** language you're comfortable in.
  Bonus points if that happens to be Python
* You're familiar with [curl](https://curl.haxx.se) for testing Restful APIs
* Your solution can ideally be launched with a single command. If not,
  instructions **must** be provided so we can test your new tool
  * Example `python3 monitor.py --support {somebody@garmin.com}`

### Overview

Servers providing Restful APIs can experience any number of issues which
ultimately prevent clients from successfully invoking their services.  E.g.:

* Hardware failure or resource exhaustion
* Operating system crash
* Web framework crash
* Internet connectivity issues
* Etc.

To this effect, *Garmin Pay* provides a health API:

```
GET https://api.qa.fitpay.ninja/health
```

which gives the collective status of all our APIs.  `health` is **always**
expected to return an HTTP status response code of `200` and a `status` field
in the  JSON response containing the value "OK". If this is ever not the case,
something is broken, and action must be taken!

Your task is to create a script to continuously monitor the health of *Garmin
Pay* using the `health` API, reporting back to Garmin support staff via email
if the service ever goes down.

### Details

* The `health` API does not require authentication
* A single failure to invoke the `health` API could have been a sporadic issue
  which doesn't warrant sending an email.  **Two failures in a row are
  considered an outage though** and should trigger an urgent email to Garmin
  support staff
* When an outage is detected, the tool should *continue* checking the health of
  the services periodically. More API failures should not result in additional
  emails to support staff, but a follow-up email should be sent when the APIs
  are healthy again (i.e., successfully invoked twice in a row)
* The emails can be either plaintext or HTML. Bonus points if they include
  interesting metrics (e.g., outage duration, average uptime between outages,
  etc.)
* All failures to invoke `health` should be logged as well as any recoveries of
  the service
* The web server is protected by a firewall which may treat repeated attempts
  to access the same resources, _without any reasonable delay between attempts_,
  as a denial-of-service attack
* Your monitor should run continuously until Ctrl-C'ed
* More details on API documentation can be found [here](https://anypoint.mulesoft.com/apiplatform/fitpay/#/portals/organizations/fd8d2eae-7955-4ec9-b009-b03635fe994b/apis/24399/versions/25936)
* Unit tests are a good idea, but not required with your submission

### Extra Credit

#### **Disclaimer** - this section is 100% optional!

Not only should *Garmin Pay*'s APIs be available from whatever
Internet-accessible machine you've deployed your monitoring script(s) to,
Garmin's watches are used all over the planet! Consequently, we also want to be
notified if the APIs become inaccessible from **any** geographic region. One way to
achieve this would be to hit the `health` API from a list of carefully selected
proxy servers. If you choose to extend your monitoring script for this, you'll
need to make some design decisions:

* Will the list of proxies be hard-coded or configurable?
* Are Garmin support email addresses region-specific?
* Etc.

Make whatever reasonable assumptions you'd like but be sure to document these.
