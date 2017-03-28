

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
      
      
      <th style="text-align: left;outline: 0px;"  ng-click="sortingRoleMenu('rolemenu.serialId')">Serial Id
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='rolemenu.serialId' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='rolemenu.serialId' && !reverse" 
    ng-click="sortingRole('rolemenu.serialId')" flex></md-icon>
    </th>
    
    <th style="text-align: left;outline: 0px;"  ng-click="sortingRoleMenu('rolemenu.menuId')">Menu Id 
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='rolemenu.menuId' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='rolemenu.menuId' && !reverse"  flex></md-icon> 
    </th>
    
        <th style="text-align: center;outline: 0px;"  ng-click="sortingRoleMenu('role.roleCode')" >Role Code	
        <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='role.roleCode' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='role.roleCode' && !reverse"  flex></md-icon>
    </th>

 		
		
		<th>  </th>
      
 
      </thead>
      <tbody>
<tr dir-paginate="role in rolemenu | orderBy:variable:reverse | filter:Roleid | itemsPerPage:10" class="tr">        
    <td style="text-align: left;">{{rolemenu.serialId}}</td>
    <td style="text-align: left;">{{rolemenu.menuId}}</td>
    <td style="text-align: right;width:200px;">{role.roleCode}}</td>
  

    <td style="text-align: right;">
               <md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="editRole($event,role)"></md-icon>
               <md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="deleteRole(role)" ></md-icon> 
    </td>
  </tr>
    </tbody>
  </table>
    </div>
</div>