


export interface AuthorGroupRequest {
    groupename: string;
    correspondingId : number;
    membresIds: number[];  
}

export interface AuthorGroup {
    id: number;
    groupename: string;
    corresponding : CorrespondingAuthor;
    membres: Author[];  
}
export interface AuthorRequest {
    name: string;
    institutionName: string;
    institutionAdress: string;
    affiliation: string;
}
export interface CorrespondingAuthor {
    id: number;
    fullname: string;
    email: string;
    role: string;
  }
export interface Author{
    id:number;
    name: string;
    institutionName: string;
    institutionAdress: string;
    affiliation: string;
}