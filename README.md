# CinemaRoomRESTService
### Spring REST service which serves to manage a small movie theatre
The application handles the following enpoints:
- `/sets` - displays available seats, price and number of rows and columns
- `/purchase` - takes seat's details from request and respond with ticket UUID if request is valid
- `/return` - takes ticket UUID from request body and respond with ticket's details
- `/stats` - takes password from request and in respond displays number of available seats, purchased tickets and total income
