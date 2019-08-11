Schemat bazy danych aplikacji CookBook


![Database Schema](./cookbook.png?raw=true "Title")

todo:
- implements categories and likes
- implement displaying application according to category and number of likes
- Change units from enum to Class//oneToMany jedna recipeIngredient ma jedną jednostkę, ale jednostka moze być dopisana do wielu recipeIngredient
- Change Categories from enum to Class // many2many recipe can belong to many categories, category may be assigned to many recipes (like recipeIngredient) 
- Implement sorting and filtering
- Add possibility of linking videos from youtube
- ##figure out how to add new units, ingredients etc while adding new recipe
- Implement Log in (basicAuth - optional)
- Implement breadcrumbs (optional) -> refactor it to a distict html element 
- Implement css : movie in the background of main page, Top Menu and footer the same on every page, CSS Grid - it will be displayed on different devices 
- Update the Data Base schema picture - it is outdated. Prepare documentation - do not have to be extensive. Just simple one.
- #Unit Test
- #Automated Tests (fronted)