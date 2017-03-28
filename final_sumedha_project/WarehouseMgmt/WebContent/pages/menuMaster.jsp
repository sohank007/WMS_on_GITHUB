
<div ng-cloak="" class="tabsdemoDynamicHeight" ng-app="MyApp">
  <md-content>
    <md-tabs md-dynamic-height="" md-border-bottom="">
    
    
  
       <!--  ********************************** MENU MASTER ************************************* -->   
  
      
      <md-tab label="Menu Master" >
        <md-content class="md-padding" ng-controller="menuMasterCtrl">
          <h1 class="md-display-2"></h1>
          <p><div layout="column" >
  <div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Menu Master</h2>
   </div>
   
         <div  layout="row" style="margin: 0px 10px;height:50px;" flex>
   				<md-input-container style="margin: 10px 10px 0px;" flex="20">
   					<label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search </label>
   					<input type="text" ng-model="ghf">
      			</md-input-container>
   				
   			 <md-input-container layout="row" style="margin: 0px 10px;" flex>     
               <md-button type="Submit" class="md-raised" 
				style="color: gray;max-height: 10px;background-color: lightgray;"
				 ng-click="addMenu($event)">
               Add Menu</md-button>
           		</md-input-container>   

				<md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex="20">
                 <dir-pagination-controls  
			       max-size="2"
			       direction-links="true"
			       boundary-links="true" >
   				 </dir-pagination-controls>
           </md-input-container>
           </div>
   

   
<div style="margin: 10px 10px;">     
    <table class="table" style="width:100%">
      <thead>
      <tr class="tr">
      
      
      <th style="text-align: left;outline: 0px;"  ng-click="sortingMenu('menu.menuId')">Menu Id
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='menu.menuId' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='menu.menuId' && !reverse" 
    ng-click="sortingMenu('menu.menuId')" flex></md-icon>
    </th>
    
    <th style="text-align: left;outline: 0px;"  ng-click="sortingMenu('menu.menuName')">Menu Name
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='menu.menuName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='menu.menuName' && !reverse"  flex></md-icon> 
    </th>
    
        <th style="text-align: center;outline: 0px;"  ng-click="sortingMenu('menu.menuIcon')" >Menu Icon	
        <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='menu.menuIcon' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='menu.menuIcon' && !reverse"  flex></md-icon>
    </th>

 		
		
		<th>  </th>
      
 
      </thead>
      <tbody>
<tr dir-paginate="menu in menumaster | orderBy:variable:reverse | filter:Menuid | itemsPerPage:10" class="tr">        
    <td style="text-align: left;">{{menu.menuId}}</td>
    <td style="text-align: left;">{{menu.menuName}}</td>
    <td style="text-align: right;width:200px;">{{menu.menuIcon}}</td>
  

    <td style="text-align: right;">
               <md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="editMenu($event,menu)"></md-icon>
               <md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="deleteMenu(menu)" ></md-icon> 
    </td>
  </tr>
    </tbody>
  </table>
    </div>
</div>
        </md-content>
      </md-tab>
   
     </tab> 
      
    
    
       <!--  ********************************** ROLE MASTER ************************************* -->
     
    
      <md-tab label="Role Master" >
     
        <md-content class="md-padding" ng-controller="RoleMasterCtrl">
          <h1 class="md-display-2"></h1>
          <p><div layout="column" >
  <div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Role Master</h2>
   </div>
   
         <div  layout="row" style="margin: 0px 10px;height:50px;" flex>
   				<md-input-container style="margin: 10px 10px 0px;" flex="20">
   					<label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search </label>
   					<input type="text" ng-model="ghf">
      			</md-input-container>
   				
   			 <md-input-container layout="row" style="margin: 0px 10px;" flex>     
               <md-button type="Submit" class="md-raised" 
				style="color: gray;max-height: 10px;background-color: lightgray;"
				 ng-click="addRole($event)">
               Add Role</md-button>
           		</md-input-container>   

				<md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex="20">
                 <dir-pagination-controls  
			       max-size="2"
			       direction-links="true"
			       boundary-links="true" >
   				 </dir-pagination-controls>
           </md-input-container>
           </div>
   

   
<div style="margin: 10px 10px;">     
    <table class="table" style="width:100%">
      <thead>
      <tr class="tr">
      
      
      <th style="text-align: left;outline: 0px;"  ng-click="sortingRole('role.roleId')">Role Id
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='role.roleId' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='role.roleId' && !reverse" 
    ng-click="sortingRole('role.roleId')" flex></md-icon>
    </th>
    
    <th style="text-align: left;outline: 0px;"  ng-click="sortingRole('role.roleRole')">Role 
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='role.roleRole' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='role.roleRole' && !reverse"  flex></md-icon> 
    </th>
    
        <th style="text-align: center;outline: 0px;"  ng-click="sortingRole('role.roleCode')" >Role Code	
        <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='role.roleCode' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='role.roleCode' && !reverse"  flex></md-icon>
    </th>

 		
		
		<th>  </th>
      
 
      </thead>
      <tbody>
<tr dir-paginate="role in rolemaster | orderBy:variable:reverse | filter:Roleid | itemsPerPage:10" class="tr">        
    <td style="text-align: left;">{{role.roleId}}</td>
    <td style="text-align: left;">{{role.role}}</td>
    <td style="text-align: right;width:200px;">{{role.roleCode}}</td>
  

    <td style="text-align: right;">
               <md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="editRole($event,role)"></md-icon>
               <md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="deleteRole(role)" ></md-icon> 
    </td>
  </tr>
  
    </tbody>
  </table>
    </div>
</div>
        </md-content>
      </md-tab>

      
 
     
          <!--  ********************************** ROLE MENU ************************************* -->
   
      
      <md-tab label="Role Menu">
        <md-content class="md-padding" ng-controller="RoleMenuCtrl">
          <h1 class="md-display-2"></h1>
          <p>

<div layout="column" >
  <div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Role Menu</h2>
   </div>
   
         <div  layout="row" style="margin: 0px 10px;height:50px;" flex>
   				<md-input-container style="margin: 10px 10px 0px;" flex="20">
   					<label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search </label>
   					<input type="text" ng-model="ghf">
      			</md-input-container>
   				
   			  <md-input-container layout="row" style="margin: 0px 10px;" flex>     
               <md-button type="Submit" class="md-raised" 
				style="color: gray;max-height: 10px;background-color: lightgray;"
				 ng-click="addMenu($event)">
               Add Role Menu</md-button>
           		</md-input-container>   

				<md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex="20">
                 <dir-pagination-controls  
			       max-size="2"
			       direction-links="true"
			       boundary-links="true" >
   				 </dir-pagination-controls>
           </md-input-container>
           </div>
   

   
<div style="margin: 10px 10px;">     
    <table class="table" style="width:100%">
      <thead>
      <tr class="tr">
      
      
    
    
      <th style="text-align: left;outline: 0px;"  ng-click="sortingRoleMenu('menuId')">Menu Id 
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='rolemenu.menuId' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='rolemenu.menuId' && !reverse"  flex></md-icon> 
    </th>
    
      <th style="text-align: left;outline: 0px;"  ng-click="sortingRoleMenu('role')"> Menu Name
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='rolemenu.role' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='rolemenu.role' && !reverse" flex></md-icon>
    </th>
    
      <th style="text-align: center;outline: 0px;"  ng-click="sortingRoleMenu('roleInfo.roleCode')" >Menu Icon	
        <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='role.roleCode' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='role.roleCode' && !reverse"  flex></md-icon>
    </th>

 		
		
		<th>  </th>
      
 
      </thead>
      <tbody>
<tr dir-paginate="rolemenu in rolemenus | orderBy:variable:reverse | filter:Roleid | itemsPerPage:10" class="tr">        
    <td style="text-align: left;">{{menuId}}</td>
    <td style="text-align: left;">{{menuName}}</td>
    <td style="text-align: center;width:200px;">{{menuIcon}}</td>
  

    <td style="text-align: right;">
               <md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="editRole($event,role)"></md-icon>
               <md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="deleteRole(role)" ></md-icon> 
    </td>
  </tr>
    </tbody>
  </table>
    </div>
</div>
        </md-content>
      </md-tab>
    </md-tabs>
  </md-content>
</div>

