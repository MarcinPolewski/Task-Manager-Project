
### Design choices
- IDs of scenes are declared in application.properties
  - enum might have been better, but I wanted to keep everything organized in one place. 
  - another possibility would be to create an enum that reads this properties and in one place stores link and autoasigns id. I think it would be too much