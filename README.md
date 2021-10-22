# Kotlin-Room-MVVM-Flow-Coroutines-DiffUtil

Room is a persistance library which you can use to save data in Android. the Room Persistance Library documentation link is as follows
Link: https://developer.android.com/training/data-storage/room

This Repository include a simple example of data saving into the database and doing CRUD operations on Employee object. If you try to add new employee you will not be able to see the delete menu in toolbar for an individual employee but if its edit/update functionality you will be able to delete the selected employee



# Home Screen with Delete all functionality
![alt text](/screenshots/home_screen.PNG)

Here as you can see i have added a recycler view which shows all the employess present in the Database. In the right hand top corner you can see the delete option. We can delete all the employees from the recycler in one go. 

# Add New Employee

![alt text](/screenshots/add_screen.PNG)

Here as you can see we can add new employee with information like Name, address and employee Id. I have used Name as primary key, but you can also use Id as Primary key. 
# Update Employee

![alt text](/screenshots/edit_screen.PNG)

Here as you can see we can Update employee with information like address and employee Id. We cannot modify the Name as its the Primary key. We can also delete the selected Employee using the delete option present on the right hand side top corner in toolbar 
