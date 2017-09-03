import { AngularBootstrapMdQuickstartPage } from './app.po';

describe('angular-bootstrap-md-quickstart App', () => {
  let page: AngularBootstrapMdQuickstartPage;

  beforeEach(() => {
    page = new AngularBootstrapMdQuickstartPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
