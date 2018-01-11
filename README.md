# Jobbsearch-App-JavafxRemake
Jobbsearch Desktop App
this is a app to help people to search for jobbs, it has many tools built into it. It has:
password storage. 
it can store all jobs you have aplied. 
search function to quickly find one of the past jobs you have applied.
it can store all interviews you have and sorts them (old/upcomming) so you can see what you have comming in the future.
it can also store work you have done and display a sum of work you have done over a month. 

most of the tables have keybinds 1,2,3,4 delete.
1,2,3,4 takes the fist/second.. object in the row you have selected and copies into your clipboard (ctrl c) so you can easily get acess to the data 

the project is built using java fxml its contructed in a mvc patern and stores all data in different txt files 
fxml view -> view controller -> txtController -> txtfile

view controller ->
this part of the application reacts with the interactions the user do with the fxml view 
its kinda lika directory of action listeners

fetches data from txtcontroller and populate the tableview with the data after that its mostly keybindings 
1 get data from first item in the slected row 
2 get data from second item in the slected row 
delete removes item from array and call txtcontroller.rewritetxt()



txt controller - >
this part of the application have controll over the txt file the txt file usualy exist of 4 parts


create file if dont exists()
if the txt file it uses dont exists it creates a new one with the same name therefore its safe to delete the txt files


getxxxxFromTxtFile()
gets the data from txt and return and a observable list of objects


addxxxxxToTxt()
send a object to it and it will be appended to the txt file (rewritetxt uses this method)


rewriteTxtFile()    // rewrite/delete
the application uses this function to remove objects from the txt file. 
you send it an observable array with objects then it deletes everything in the txt and rewrites it with the content you gave it 

remove the content you wanna remove from array  -> send it to rewriteTxtFile -> delete content of txt - > loop addxxxtotxt



