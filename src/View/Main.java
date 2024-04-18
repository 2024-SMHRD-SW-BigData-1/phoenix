package View;

import java.util.Random;
import java.util.Scanner;

import Controller.Controller;
import Model.DTO;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("[게임]");
		
		int index = 0; // 문제 번호
		String answer = null; // 유저의 입력 값
		boolean launch = true; // 반복문의 실행 조건
		int cnt = 3;	// 총 기회
		int sum = 0;	// 총 점수

		while (launch) { // 실행 시 첫 화면
			System.out.println("1. 회원가입 2. 로그인");
			int select = s.nextInt(); // 선택 사항 입력

			Controller con;

			switch (select) { // 선택 시 화면
				default: // 1, 2 외에 다른 '숫자' 를 입력 했을 경우
					System.out.println("다시 선택해주세요."); 
					
					break;
				case 1: // 1. 회원가입을 선택 했을 시 유저 정보 입력
					System.out.println("==== 회원가입 ====");
					System.out.println("사용 할 ID :");
					String id = s.next();
					System.out.println("사용 할 PW :");
					String pw = s.next();
					System.out.println("이름 :");
					String name = s.next();
					System.out.println("나이 :");
					int age = s.nextInt();
					System.out.println("사용 할 닉네임 :");
					String nickname = s.next();

					DTO dto = new DTO(id, pw, name, age, nickname);
					// 유저정보를 DTO 자료형에 담아 
					con = new Controller();
					con.sign_up(dto);
					// dto로 전달
					break;

				case 2: // 로그인을 선택 했을 시 ID, PW 입력
					System.out.print("ID : ");
					id = s.next();
					System.out.print("PW : ");
					pw = s.next();
					dto = new DTO(id, pw);
					// 입력 값을 DTO 자료형에 담아
					con = new Controller();
					System.out.println(con.login(dto));
					// 로그인 여부 출력 (일치하다면 로그인, 일치하지 않는다면 종료)

					if (con.login(dto).equals("로그인 성공")) { // 로그인 성공시
						System.out.println("==== 게임 룰 ====");
						System.out.println("기회는 총 세 번!!"); 
						System.out.println("게임시작을 누르시고 드라마 혹은 영화 카테고리를 선택해주세요");
						System.out.println("선택하시면 문제가 출제가 됩니다.");
						System.out.println("노래를 듣고 해당 노래가 나오는 드라마(영화) 제목을 맞춰주세요.");
						System.out.println("[1]게임시작  [2]게임종료  [3]랭킹보기");
						int select2 = s.nextInt();
						// 게임 룰 설명, 선택지 출력
							
							while (launch) {
								
								if (select2 == 1) { // 게임시작을 선택한 경우 
									System.out.println("[1]드라마  [2]영화"); // 카테고리 출력
									int select3 = s.nextInt();

										if (select3 == 1) { // 드라마 선택
											System.out.println("노래를 듣고 드라마 제목을 맞춰주세요.");
											System.out.println("드라마 ost 재생 중");
											s.nextLine();
											con = new Controller();
											con.drama_music_play(index);
											// 드라마 OST 출력
											
											System.out.println((index + 1) + "번 문제 : ");

											while (launch) {
												System.out.print("정답 : ");
												answer = s.nextLine();
												// 정답 입력
												String q1 = con.questionsDrama(index);
												// 입력값 전달
												
												if (answer.equals(q1)) { // 입력 값과 정답이 같다면
													con.musicStop();	
													System.out.println("정답입니다");
													System.out.println(" ");
						                            sum += Integer.parseInt(con.dramaPoints(index));
						                            con = new Controller();
						                            con.sumMovie(dto, sum);
						                            // 음악 종료, 문구 출력, 각 문제당 점수를 합산하여 전달
						                            
						                            					  // 인덱스 값은 문제의 번호
														if (index != 9) { // 마지막(10번째) 문제가 아니라면
															index++;	  // 문제 번호 + 1
															con.drama_music_play(index);  // 해당 문제의 OST를 재생
															System.out.println("======== " + (index + 1) + "번 문제" + " ========");
																
														}else{ // 마지막 문제가 맞다면
															System.out.println("======== 총 획득 점수 ========");
															dto = new DTO(id);
															con = new Controller();
															con.userRanking(dto);
															// 유저의 총 점수를 출력 및 전달
														
															System.out.println("===========================");
															System.out.println();
															System.out.println("======== 랭킹 순위 ========");
															con.ranking();
															con.musicStop();
															// 랭킹을 출력하고, 음악을 종료
															
															System.out.println("게임종료");
															launch = false;
															break;
															// 게임을 종료.
														}
															
												}else{ // 입력 값이 정답과 다르다면
													cnt--; // 기회가 1회 감소 (총 10개)
															
													if (cnt >= 0) { // 기회가 0보다 크거나 같다면
														System.out.println("땡! 기회가 " + cnt + "번 남았습니다.");
														// 잔여 기회 출력
																		
														if (cnt == 0) { // 기회가 0이라면
															System.out.println("실패! 게임이 종료됩니다.");
															System.out.println("======== 총 획득 점수 ========");
															dto = new DTO(id);
															con = new Controller();
															con.userRanking(dto);
															// 유저의 총 점수를 출력 및 전달
															
															System.out.println("=========================== \n");
						                                    System.out.println("======== 랭킹 순위 ========");
															con = new Controller();
															con.ranking();
															con.musicStop();
															// 랭킹을 출력하고, 음악을 종료
															
															launch = false;				
															break;
																	}
													}
												}
											}
											
										}else if(select3 == 2) { // 영화 선택
											System.out.println("노래를 듣고 영화 제목을 맞춰주세요.");
											System.out.println("======== " + (index + 1) + "번 문제"+ " ========");
											System.out.println("영화 ost 재생 중");
											s.nextLine();
											con = new Controller();
											con.movie_music_play(index);
											// 영화 OST 재생
											
											System.out.println((index + 1) + "번 문제 : ");
											
											while (launch) {
												System.out.print("정답 : ");
												answer = s.nextLine();
												// 정답 입력
												
												String q2 = con.questionsMovie(index);
												// 입력값 전달
												
												if (answer.equals(q2)) { // 입력 값과 정답이 같다면
													con.musicStop();
													System.out.println("정답입니다");
													System.out.println(" ");
						                            sum += Integer.parseInt(con.moviePoints(index));
						                            con = new Controller();
						                            con.sumMovie(dto, sum);
						                            // 음악종료, 문구 출력, 각 문제당 점수를 합산하여 전달

						                            				  // 인덱스 값은 문제의 번호
													if (index != 9) { // 마지막 문제가 아니라면
														index++;	  // 문제 번호 + 1
														con.movie_music_play(index); // 해당 문제의 OST를 재생
														System.out.println("======== " + (index + 1) + "번 문제 ========");
														
													}else{ // 마지막 문제가 맞다면
														System.out.println("======== 총 획득 점수 ========");
														dto = new DTO(id);
														con = new Controller();
														// 유저의 총 점수를 출력 및 전달
														
														con.userRanking(dto);
														System.out.println("===========================");
														System.out.println();
														System.out.println("======== 랭킹 순위 ========");
														con.musicStop();
														con.ranking();
														// 랭킹을 출력하고 음악을 종료
														
														System.out.println("게임종료");
														launch = false;
														break;
														// 게임을 종료
													}
											}else{ // 입력 값이 정답과 다르다면
												cnt--; // 기회가 1회 감소 (총 10개)
												
												if (cnt >= 0) { // 기회가 0보다 크거나 같다면
													System.out.println("땡! 기회가 " + cnt + "번 남았습니다.");
													// 잔여 기회 출력
													
													if (cnt == 0) { // 기회가 0이라면
														con.musicStop();
														System.out.println("실패! 게임이 종료됩니다.");
														System.out.println("======== 총 획득 점수 ========");
														dto = new DTO(id);
														con = new Controller();
														con.userRanking(dto);
														// 유저의 총 점수를 출력 및 전달
														
														System.out.println("=========================== \n");
														System.out.println("======== 랭킹 순위 ========");
														con.ranking();
														// 랭킹을 출력하고, 음악을 종료
														
														launch = false;
														break;
													}
												}
											}
										}
								}
										
								}else if (select2 == 2){ // 만약 로그인 후 2번(게임종료)을 선택했다면
									System.out.println("게임 종료");
									launch = false; // 게임 종료
									
								}else if (select2 == 3){ // 만약 로그인 후 3번(랭킹보기)을 선택했다면
									 System.out.println("======== 나의 순위 ========");
									 dto = new DTO(id);
				                     con = new Controller();
				                     con.userRanking(dto);
				                     // 나의 순위 출력
				                     
				                     System.out.println("===========================");
				                     System.out.println();
				                     System.out.println("======== 랭킹 순위 ========");
				                     con.ranking();
				                     // 상위 10명 랭킹 출력
				                     
				                     launch = false;
								}
						}
					}else{ // 로그인에 실패 했을 경우
						launch = false;// 게임 종료
						
					}
				}
			}
		}
		}
