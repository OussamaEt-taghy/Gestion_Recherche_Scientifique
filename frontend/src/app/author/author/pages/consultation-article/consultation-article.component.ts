import { ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { UserStorageService } from 'src/app/auth/Service/user-storage.service';

@Component({
  selector: 'app-consultation-article',
  templateUrl: './consultation-article.component.html',
  styleUrls: ['./consultation-article.component.scss']
})
export class ConsultationArticleComponent {
  

  /*articles: Article[] = [];

  filteredGroups: Article[] = [];

  paginated: Article[] = []; 
  pageSize = 3; 
  pageIndex = 0;
  pageSizeOptions = [3, 5, 10]; 

  articleDialogRef!: MatDialogRef<any>;
  dialogRef!: MatDialogRef<any>;

  addArticleForm!: FormGroup;
  updateArticleForm!: FormGroup;
 
   

  @ViewChild('addArticleDialog') addGroupDialog!: TemplateRef<any>;  
  @ViewChild('confirmDialog') confirmDialog!: TemplateRef<any>;
  @ViewChild('updateArticleDialog') updateGroupDialog!: TemplateRef<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;  

 

  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private cdRef: ChangeDetectorRef,
    private userStorageService: UserStorageService
  ) {}


  ngOnInit(): void {
    this.initializeForms();
    this.loadArticle();
  }
  ngAfterViewChecked(): void {
    this.cdRef.detectChanges(); 
  }
  initializeForms(): void {
    this.addArticleForm = this.fb.group({
     
    });
  }
   openAddArticlePopup(): void {
  }
  openUpdateArticlePopup(): void {
  
}
  loadArticle(): void {
 
  }
  updatePaginatedGroups(): void {
   
  }
  onPageChange(): void {
   
  }
  addArticle(): void {
   
  }
  updateArticle(): void {
  }
   removeArticle(): void {
   
  }
  closeDialogArticle():void {
  }
*/
  }
