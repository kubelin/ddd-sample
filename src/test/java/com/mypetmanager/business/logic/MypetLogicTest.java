package com.mypetmanager.business.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * com.mypetmanager.business.logic
 * <p>
 * MyPetLogicTest
 * description : MyPetLogicTest
 *
 * @author 	kubel
 * @version
 * @since 	2024. 8. 18.
 *
 * <pre>
 * [ history of modify ]
 *      수정일        수정자           수정내용
 *  ----------    -----------    ---------------------------
 *  2024. 8. 18.     kubel 		   수정내용작성
 * </pre>
 *
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("MypetLogicTest")
public class MypetLogicTest {

	@Nested
	@DisplayName("팻 저장 메소드")
	class Describe_save {

		@BeforeEach
		@DisplayName("기존의 테스트 데이터를 전체 삭제한다")
		void prepare() {
			//			repository.deleteAll();
		}

		@Nested
		@DisplayName("팻 객체가 주어지면")
		class Context_with_a_member {
			//final Pet myPet = new Pet();

			@Test
			@DisplayName("팻 객체를 저장하고, 저장된 객체를 리턴한다.")
			void it_saves_obj_and_returns_a_saved_obj() {
				//				Assertions.assertNull(givenMember.getId());
				//
				//				final Member saved = repository.save(givenMember);
				//
				//				Assertions.assertNotNull(saved.getId());
				//				Assertions.assertEquals(saved.getName(), givenName);
			}
		}
	}

	@Nested
	@DisplayName("increase 메소드 - JPA 어노테이션이 없어 실패하는 테스트")
	class Describe_increase {
		@Nested
		@DisplayName("저장된 멤버 객체의 아이디와 증가할 숫자가 주어지면")
		class Context_with_a_member {
			final int givenNumber = 3;
			//			Member givenMember;
			//
			//			long givenId() {
			//				return givenMember.getId();
			//			}

			@BeforeEach
			void preapre() {
				//				givenMember = repository.save(new Member(givenName, 0));
			}

			@Test
			@DisplayName("방문 카운트를 증가시키고, 업데이트된 레코드 수를 리턴한다")
			void it_increases_count_and_returns_count_of_updated_records() {
				//				Assertions.assertEquals(givenMember.getVisited(), 0,
				//					"실행 전의 카운트는 0 이다");
				//
				//				final int updatedRecords = repository.increaseCount(givenId(), givenNumber);
				//
				//				Assertions.assertEquals(updatedRecords, 1,
				//					"한 건이 업데이트되었다");
				//				Assertions.assertEquals(
				//					givenNumber,
				//					repository.findById(givenId()).get().getVisited(),
				//					"주어진 증가 숫자만큼 방문 카운트가 증가한다");
			}
		}
	}

}
