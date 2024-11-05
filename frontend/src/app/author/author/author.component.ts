import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, ViewChild, AfterViewInit, HostListener, ChangeDetectorRef, OnInit } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { filter } from 'rxjs/operators';
import { UserStorageService } from 'src/app/auth/Service/user-storage.service';

@UntilDestroy()
@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.scss']
})
export class AuthorComponent implements OnInit, AfterViewInit {
  isMobile: boolean = false;
  username: string | undefined;
  currentYear: number;
  sidenavMode: 'side' | 'over' = 'side';
  sidenavOpened = true;

  @ViewChild(MatSidenav) sidenav!: MatSidenav;

  constructor(
    private observer: BreakpointObserver,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) {
    this.currentYear = new Date().getFullYear();
    this.updateLayoutForScreenSize();
  }

  private updateLayoutForScreenSize() {
    this.isMobile = window.innerWidth < 768;
    this.sidenavMode = this.isMobile ? 'over' : 'side';
    this.sidenavOpened = !this.isMobile;
  }

  @HostListener('window:resize')
  onResize() {
    this.updateLayoutForScreenSize();
    this.cdr.detectChanges();
  }

  ngOnInit() {
    this.route.params.pipe(untilDestroyed(this)).subscribe(params => {
      this.username = decodeURIComponent(params['username']);
      this.cdr.detectChanges(); 
    });
  }

  ngAfterViewInit() {
    this.observer
    .observe([Breakpoints.Handset, Breakpoints.Tablet])
    .pipe(untilDestroyed(this))
    .subscribe((res) => {
      setTimeout(() => {
        this.isMobile = res.matches;
        this.sidenavMode = this.isMobile ? 'over' : 'side';
        this.sidenavOpened = !this.isMobile;
        this.cdr.detectChanges();
      });
    });

    this.router.events
      .pipe(
        untilDestroyed(this),
        filter((e) => e instanceof NavigationEnd)
      )
      .subscribe(() => {
        if (this.isMobile && this.sidenav?.opened) {
          this.sidenav.close();
        }
      });
  }

  logout(): void {
    UserStorageService.signOut();
    this.router.navigateByUrl('login');
  }

  openSettings(): void {
    this.router.navigate(['/settings']);
  }

  closeSidenavIfMobile() {
    if (this.isMobile && this.sidenav?.opened) {
      this.sidenav.close();
    }
  }
}