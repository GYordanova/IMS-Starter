INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`items` (`Itemname` , `Itemmake` , `Itemprice`) VALUES ('chrisps', 'walkers',0.79);
INSERT INTO `ims`.`items` (`Itemname` , `Itemmake` , `Itemprice`) VALUES ('chrispy', 'walky',1);
INSERT INTO `ims`.`order` (`Customerid`) VALUES (1);
INSERT INTO `ims`.`orderline` (`Orderid`,`Itemid`) VALUES (1,1);