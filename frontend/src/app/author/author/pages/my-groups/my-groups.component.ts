import { ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthorServicesService } from 'src/app/author/Service/author-services.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Author, AuthorGroup, AuthorGroupRequest, AuthorRequest } from 'src/app/author/Models/AuthorGroupe';
import { UserStorageService } from 'src/app/auth/Service/user-storage.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';


@Component({
  selector: 'app-my-groups',
  templateUrl: './my-groups.component.html',
  styleUrls: ['./my-groups.component.scss']
})
export class MyGroupsComponent implements OnInit {
  groups: AuthorGroup[] = [];

  filteredGroups: AuthorGroup[] = [];

  paginatedGroups: AuthorGroup[] = []; 
  pageSize = 3; 
  pageIndex = 0;
  pageSizeOptions = [3, 5, 10]; 
  members: Author[] = [];
  Authors:Author[]=[];
  memberDialogRef!: MatDialogRef<any>;
  groupeDialogRef!: MatDialogRef<any>;
  dialogRef!: MatDialogRef<any>;

  addGroupForm!: FormGroup;
  addMemberForm!: FormGroup;
  updateGroupForm!: FormGroup;
  updateMemberForm!:FormGroup;
  

  @ViewChild('addGroupDialog') addGroupDialog!: TemplateRef<any>;  
  @ViewChild('addMemberDialog') addMemberDialog!: TemplateRef<any>; 
  @ViewChild('confirmDialog') confirmDialog!: TemplateRef<any>;
  @ViewChild('updateGroupDialog') updateGroupDialog!: TemplateRef<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;  
  @ViewChild('confirmDialogMembre') confirmDialogMembre!: TemplateRef<any>;
 

  constructor(
    private fb: FormBuilder,
    private groupsService: AuthorServicesService,
    public dialog: MatDialog,
    private cdRef: ChangeDetectorRef,
    private userStorageService: UserStorageService
  ) {}


  ngOnInit(): void {
    this.initializeForms();
    this.loadGroups();
    this.loadMembres();
  }
  ngAfterViewChecked(): void {
    this.cdRef.detectChanges(); 
  }
  

  initializeForms(): void {
    this.addGroupForm = this.fb.group({
      groupName: ['', Validators.required],
      members: [[]]
    });

    this.addMemberForm = this.fb.group({
      name: ['', Validators.required],
      affiliation: ['', Validators.required],
      institutionName: ['', Validators.required],
      institutionAdress: ['', Validators.required]
    });
  }
 
   openAddgroupPopup(): void {
    const dialogConfig = {
      width: '500px', 
      height: 'auto', 
      panelClass: 'custom-dialog-container' 
    };
    this.groupeDialogRef = this.dialog.open(this.addGroupDialog,dialogConfig);
  }

  isMember(authorId: number, members: Author[]): boolean {
    return members.some(member => member.id === authorId);
  }
  
 // Ajoutez cette méthode dans votre composant
searchGroups(searchTerm: string): void {
  if (searchTerm.trim() === '') {
    // Si aucun texte de recherche, affiche tous les groupes
    this.filteredGroups = [...this.groups];
  } else {
    // Filtrer les groupes dont le nom correspond au terme de recherche
    this.filteredGroups = this.groups.filter(group =>
      group.groupename.toLowerCase().includes(searchTerm.toLowerCase())
    );
  }
  // Mettre à jour les groupes paginés pour refléter les résultats filtrés
  this.updatePaginatedGroups();
}

  openUpdateGroupPopup(group: AuthorGroup): void {
    console.log('Group passed to openUpdateGroupPopup:', group);
    if (!group) {
        console.log('Error: Group is undefined in openUpdateGroupPopup');
        return;
    }

    const dialogConfig = {
        width: '500px',
        height: 'auto',
        panelClass: 'custom-dialog-container',
        data: group
    };

    // Initialize updateGroupForm with selected group data
    this.updateGroupForm = this.fb.group({
        groupName: [group.groupename, Validators.required],
        members: [group.membres.map(m => m.id)]  // Pre-select members by ID
    });

    this.groupeDialogRef = this.dialog.open(this.updateGroupDialog, dialogConfig);
}

  openAddMemberPopup(): void {
    const dialogConfig = {
      width: '400px', 
      height: 'auto', 
      panelClass: 'custom-dialog-container'
    };
    this.memberDialogRef = this.dialog.open(this.addMemberDialog, dialogConfig);
  }

  ///
  loadGroups(): void {
    this.groupsService.getAllGroups().subscribe({
      next: (groups: AuthorGroup[]) => {
        this.groups = groups;
        this.filteredGroups = [...groups];
        this.updatePaginatedGroups(); // Met à jour les groupes paginés
        console.log('Groupes récupérés:', this.groups);
        
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des groupes:', error);
        // Ajoutez ici la gestion des erreurs (ex: message d'erreur à l'utilisateur)
      }
    });
  }
 

  updatePaginatedGroups(): void {
    const startIndex = this.pageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.paginatedGroups = this.filteredGroups.slice(startIndex, endIndex);
  }
  

  // Modify page change method to work with filtered groups
  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePaginatedGroups();
  }

 
  addGroup(): void {
    if (this.addGroupForm.invalid) {
      console.log('Form is invalid');
      return;
    }
  
    const newGroup: AuthorGroupRequest = {
      groupename: this.addGroupForm.get('groupName')?.value,
      membresIds: this.addGroupForm.get('members')?.value || [],
      correspondingId : this.userStorageService.getUserId() 
    };
  
    this.groupsService.createGroup(newGroup).subscribe({
      next: (response) => {
        console.log('Groupe ajouté avec succès:', response);
        this.loadGroups(); // Reload the list of groups
        this.closeDialogGroupe(); // Close the dialog on success
      },
      error: (error) => {
        console.error("Erreur lors de l'ajout du groupe:", error);
      }
    });
  }
  

  updateGroup(group: AuthorGroup): void {
    if (!group || !group.id) {
        console.log('Update aborted: Group or Group ID is missing.');
        return;
    }

    if (this.updateGroupForm.invalid) {
        console.log('Update aborted: Form is invalid.', this.updateGroupForm.errors);
        return;
    }

    const updatedGroup: AuthorGroupRequest = {
        groupename: this.updateGroupForm.value.groupName,
        membresIds: this.updateGroupForm.value.members.map((id: number) => ({ id })),
        correspondingId: this.userStorageService.getUserId()
    };

    console.log('Calling update service with group ID:', group.id);
    this.groupsService.updateGroup(group.id, updatedGroup).subscribe({
        next: (updated) => {
            console.log('Group updated successfully:', updated);
            this.loadGroups();
            this.closeDialogGroupe();
        },
        error: (error) => {
            console.error('Update failed:', error);
        }
    });
}


   // remove a groupe
   removeGroup(groupId: number): void {
    this.dialogRef = this.dialog.open(this.confirmDialog, {
      width: '300px',
      data: {
        message: 'Êtes-vous sûr de vouloir supprimer ce groupe ?'
      }
    });

    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Call the service to delete the group
        this.groupsService.deleteGroup(groupId).subscribe({
          next: () => {
            this.loadGroups(); // Refresh the list
            console.log('Groupe supprimé avec succès.');
          },
          error: (error) => {
            console.error('Erreur lors de la suppression du groupe:', error);
          }
        });
      } else {
        console.log('Suppression annulée.');
      }
    });
  }

 // remove a groupe
 removeMembre(memberId: number): void {
  this.dialogRef = this.dialog.open(this.confirmDialogMembre, {
    width: '300px',
    data: {
      message: 'Êtes-vous sûr de vouloir supprimer ce membre ?'
    }
  });


  this.dialogRef.afterClosed().subscribe(result => {
    if (result) {
      // Call the service to delete the group
      this.groupsService.deleteAuthor(memberId).subscribe({
        next: () => {
          this.loadMembres(); // Refresh the list
          console.log('Membre supprimé avec succès.');
        },
        error: (error) => {
          console.error('Erreur lors de la suppression du Membre:', error);
        }
      });
    } else {
      console.log('Suppression annulée.');
    }
  });
}



  

  closeDialogGroupe():void {
    if(this.groupeDialogRef){
      this.groupeDialogRef.close();
    }
    
  }
  closeDialogMembres(): void {
    if (this.memberDialogRef) {
      this.memberDialogRef.close();
    }
  }

  ////  
 

  addNewMember():void {
    if (this.addMemberForm.invalid) {
      console.log('Form is invalid');
      return;
    }
    const NewMember: AuthorRequest = {
      name: this.addMemberForm.get('name')?.value,
      institutionName:this.addMemberForm.get('institutionName')?.value,
      institutionAdress:this.addMemberForm.get('institutionAdress')?.value,
      affiliation:this.addMemberForm.get('affiliation')?.value
     
    };
  
    this.groupsService.addAuthor(NewMember).subscribe({
      next: (response) => {
        console.log('Membre ajouté avec succès:', response);
        this.loadMembres();
        const currentMembers = this.addGroupForm.get('members')?.value || [];
        this.addGroupForm.get('members')?.setValue([...currentMembers, response.id]); 
        this.closeDialogMembres(); 
      },
      error: (error) => {
        console.error("Erreur lors de l'ajout du groupe:", error);
      }
    });

  }

  loadMembres(){
    this.groupsService.getAllAuthors().subscribe({
      next: (authors: Author[]) => {
        this.Authors = authors;
        console.log('les auteurs récupérés:', this.Authors);
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des groupes:', error);
        
      }
    });

  }
  updateMembres(){

  }



 

}
