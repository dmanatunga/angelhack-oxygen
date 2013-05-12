

def main():
	files = ['bcards.txt', 'bcards1.txt', 'bcards2.txt']
	str_file = open('bcards_strings.txt', 'w')
	arr_file = open('bcards_array.txt', 'w')
	arr_file.write('<string-array name="black_cards">\n')
	count = 0
	for file_name in files:
		in_file = open(file_name)	
		for line in in_file:
			strings = line.split('<>')
			for val in strings:	
				val = val.replace('\'', '\\\'')
				str_id = 'bcard_%d' % count
				str_file.write('<string name="%s">%s</string>\n' % (str_id, val))
				arr_file.write('\t<item>@string/%s</item>\n' % str_id)
				count = count+1
	arr_file.write('</string-array>\n')

if __name__ == "__main__":
	main()
