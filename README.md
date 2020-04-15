| Client Request | Server Response Code | Server Response Message
| -------------- |:-------------------- |:-----------------------
| 800 Login      | 801                  | Login successful.
|       | 802                 | Login Denied.
|       | 808                 | User is already logged in.
| 400 Logoff      | 401                  | Success.
|       | 402                  | Failed.
| 700 SignUp      | 701                  | SignUp successful.
|       | 702                  | SignUp unsuccessful.
| 600 Upload      | 601                  | Upload successful.
|       | 602                  | Upload unsuccessful.
| 500 Download      | 501                  | Download successful.
|       | 502                  | Download unsuccessful.