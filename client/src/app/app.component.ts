import { Component} from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  private showMenu:Boolean;


  hideAdminMenu(): void {
    this.showMenu = false;
  }


  showAdminMenu(): void {
    this.showMenu = true;
  }



}
