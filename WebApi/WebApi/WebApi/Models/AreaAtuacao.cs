using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class AreaAtuacao
    {
        public AreaAtuacao()
        {
            AreaAtendimento = new HashSet<AreaAtendimento>();
        }

        public int CdAreaatuacao { get; set; }
        public string DsAreaatuacao { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public ICollection<AreaAtendimento> AreaAtendimento { get; set; }
    }
}
